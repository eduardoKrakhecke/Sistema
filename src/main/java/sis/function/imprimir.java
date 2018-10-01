package sis.function;

import com.fasterxml.jackson.databind.node.ObjectNode;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;
import org.hibernate.internal.SessionImpl;
import org.springframework.http.HttpHeaders;
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
import java.io.IOException;
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
        int parametroUf = 0;
        int parametroMunicipio = 0;
        String ordenacao;
        ordenacao = parametrosImpressao.get("parametrosImpressao").get("ordem").asText();

            if(parametrosImpressao.get("parametrosImpressao").get("filtro").asText()!= null){
                parametroUf = parametrosImpressao.get("parametrosImpressao").get("filtro").get("uf").asInt();
            }
            if(parametrosImpressao.get("parametrosImpressao").get("filtro").get("municipio")!=null){
                parametroMunicipio = parametrosImpressao.get("parametrosImpressao").get("filtro").get("municipio").asInt();
            }

            parametrosJasper.put("ordenacao", ordenacao);
            parametrosJasper.put("municipio", parametroMunicipio);
            parametrosJasper.put("uf", parametroUf);
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }

}
