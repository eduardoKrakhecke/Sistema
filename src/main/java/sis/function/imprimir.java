package sis.function;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.istack.internal.Nullable;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;
import org.hibernate.internal.SessionImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class imprimir {



    @PersistenceContext
    private EntityManager em;

    @RequestMapping(method = RequestMethod.POST, value = "/imprimirCliente", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> gerarListaCliente(@RequestBody ObjectNode parametrosImpressao,  HttpServletResponse response) throws NumberFormatException, Exception {
        String mensagem="";
        InputStream arquivoJasper=null;
        byte[] bytes = null;
        Map<String, Object> parametrosJasper = new HashMap<>();
        long parametroUfInicial = 0;
        long parametroUfFinal = 0;
        long parametroMunicipioInicial = 0;
        long parametroMunicipioFinal = 0;
            /*  tratar null*/
            if(parametrosImpressao.get("parametrosImpressao").get("")!= null){
                parametroUfInicial = parametrosImpressao.get("parametrosImpressao").get("filtro").get("uf").asLong();
                parametroUfFinal =  parametrosImpressao.get("parametrosImpressao").get("filtro").get("uf").asLong();
            }
            else{
                parametroUfFinal = 99999999999999L;
            }
            if( parametrosImpressao.get("parametrosImpressao").get("filtro").get("municipio")!=null){
                parametroMunicipioInicial = parametrosImpressao.get("parametrosImpressao").get("filtro").get("municipio").asLong();
                parametroMunicipioFinal =  parametrosImpressao.get("parametrosImpressao").get("filtro").get("municipio").asLong();
            }
            else{
                parametroMunicipioFinal = 999999999999L;
            }

            parametrosJasper.put("idMunicipioInicial", parametroMunicipioInicial);
            parametrosJasper.put("idMunicipioFinal", parametroMunicipioFinal);
            parametrosJasper.put("idUfInicial", parametroUfInicial);
            parametrosJasper.put("idUfFinal", parametroUfFinal);
            arquivoJasper = this.getClass().getResourceAsStream("/static/module/listaClienteComponent/relatorioCliente.jasper");

        try {
            Connection conexao = em.unwrap(SessionImpl.class).connection();
            JasperReport arquivoJasperCarregado  = (JasperReport) JRLoader.loadObject(arquivoJasper);
            bytes = JasperRunManager.runReportToPdf(arquivoJasperCarregado, parametrosJasper, conexao);
            response.reset();
            response.resetBuffer();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream ouputStream = response.getOutputStream();
            ouputStream.write(bytes, 0, bytes.length);
            ouputStream.flush();
            ouputStream.close();

            conexao.close();
            conexao = null;

        } catch (JRException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }

}
