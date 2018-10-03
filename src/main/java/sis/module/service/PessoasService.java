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
import sis.module.model.Pessoas;
import sis.module.repository.PessoasRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class PessoasService {

    @Autowired
    PessoasRepository pessoasRepository;

    @Transactional
    public Pessoas cadastrar(Pessoas pes) {
        return pessoasRepository.save(pes);
    }

    public void excluir(Pessoas pes) {
        pessoasRepository.delete(pes);
    }

    public Pessoas buscaPorId(long id) {
        return pessoasRepository.findOne(id);
    }

    @PersistenceContext
    private EntityManager em;

    public Pagina pessoasPaginadas(int page, int size, String filtro) {
        Criteria criteria = em.unwrap(Session.class).createCriteria(Pessoas.class);
        Criteria criteriaCount =  em.unwrap(Session.class).createCriteria(Pessoas.class);
        criteria.createAlias("uf", "uf");
        criteriaCount.createAlias("uf", "uf");
        criteria.createAlias("municipio", "municipio");
        criteriaCount.createAlias("municipio", "municipio");
       /* criteria.add(Restrictions.eq("entidade.idEntidade", usuarioEntidade));
        criteriaCount.add(Restrictions.eq("entidade.idEntidade", usuarioEntidade));
*/
        if ((filtro != null) && (filtro != "")) {
            criteria.add(
                    Restrictions.or(Restrictions.ilike("uf.nome", filtro, MatchMode.ANYWHERE),Restrictions.ilike("nome", filtro, MatchMode.ANYWHERE),
                            Restrictions.or(Restrictions.ilike("municipio.nome", filtro, MatchMode.ANYWHERE),Restrictions.ilike("nome", filtro, MatchMode.ANYWHERE))
            ));
            criteriaCount.add(
                    Restrictions.or(Restrictions.ilike("uf.nome", filtro, MatchMode.ANYWHERE),
                            Restrictions.or(Restrictions.ilike("municipio.nome", filtro, MatchMode.ANYWHERE),
                    Restrictions.ilike("nome", filtro, MatchMode.ANYWHERE))));
        }
        criteria.setFirstResult(page * size).setMaxResults(size).addOrder(Order.asc("nome"));

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
