package sis.module.service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sis.model.Pagina;
import sis.module.model.Produtos;
import sis.module.repository.ProdutosRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProdutosService {

    @Autowired
    ProdutosRepository produtosRepository;

    @Transactional
    public Produtos cadastrar(Produtos pro) {
        return produtosRepository.save(pro);
    }

    public Produtos buscaPorId(long id) {
        return produtosRepository.findOne(id);
    }

    public void excluir(Produtos pro) {
        produtosRepository.delete(pro);
    }

    @PersistenceContext
    private EntityManager em;

    public Pagina produtosPaginados(int page, int size, String filtro) {
        Criteria criteria = em.unwrap(Session.class).createCriteria(Produtos.class);
        Criteria criteriaCount =  em.unwrap(Session.class).createCriteria(Produtos.class);
       /* criteria.add(Restrictions.eq("entidade.idEntidade", usuarioEntidade));
        criteriaCount.add(Restrictions.eq("entidade.idEntidade", usuarioEntidade));
*/
        if ((filtro != null) && (filtro != "")) {
            criteria.add(Restrictions.ilike("descricao", filtro, MatchMode.ANYWHERE));
            criteriaCount.add(Restrictions.ilike("descricao", filtro, MatchMode.ANYWHERE));
        }
        criteria.setFirstResult(page * size).setMaxResults(size).addOrder(Order.asc("descricao"));

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

    public List<Produtos> buscarProdutosAutoComplete(String parametro){
        return produtosRepository.search(parametro);
    }
}
