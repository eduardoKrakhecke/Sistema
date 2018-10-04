package sis.module.service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sis.model.Pagina;
import sis.module.model.Estoques;
import sis.module.repository.EstoquesRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class EstoquesService {

    @Autowired
    EstoquesRepository estoquesRepository;

    @Transactional
    public Estoques cadastrar(Estoques est) {
        return estoquesRepository.save(est);
    }

    public void excluir(Estoques est) {
        estoquesRepository.delete(est);
    }

    public Estoques buscaPorId(long id) {
        return estoquesRepository.findOne(id);
    }

    @PersistenceContext
    private EntityManager em;

    public Pagina estoquePaginado(int page, int size, String filtro) {
        Criteria criteria = em.unwrap(Session.class).createCriteria(Estoques.class);
        Criteria criteriaCount =  em.unwrap(Session.class).createCriteria(Estoques.class);
        criteria.createAlias("produto", "produto");
        criteriaCount.createAlias("produto", "produto");
       /* criteria.add(Restrictions.eq("entidade.idEntidade", usuarioEntidade));
        criteriaCount.add(Restrictions.eq("entidade.idEntidade", usuarioEntidade));
*/
        if ((filtro != null) && (filtro != "")) {
            criteria.add(
                    Restrictions.or(Restrictions.ilike("produto.descricao", filtro, MatchMode.ANYWHERE),Restrictions.ilike("descricao", filtro, MatchMode.ANYWHERE),
                            Restrictions.or(Restrictions.ilike("produto.descricao", filtro, MatchMode.ANYWHERE),Restrictions.ilike("descricao", filtro, MatchMode.ANYWHERE))
                    ));
            criteriaCount.add(
                    Restrictions.or(Restrictions.ilike("produto.descricao", filtro, MatchMode.ANYWHERE),
                                    Restrictions.ilike("descricao", filtro, MatchMode.ANYWHERE)));
        }
        criteria.setFirstResult(page * size).setMaxResults(size).addOrder(Order.asc("produto.descricao"));

        criteriaCount.setProjection(Projections.rowCount());
        Long count = (Long) criteriaCount.uniqueResult();

        Pagina pagina = new Pagina();
        pagina.setConteudo(criteria.list());
        pagina.setNumeroDaPagina(page);
        pagina.setTamanhoDaPagina(size);
        pagina.setQuantidadeDeRegistros(count);
        pagina.setQuantidadeDePaginas(pagina.getQuantidadeDeRegistros() / size);
        pagina.setUltimaPagina(page == pagina.getQuantidadeDePaginas());
        pagina.setPrimeiraPagina(page == 0);
        return pagina;
    }

}
