package br.com.zup.mercadolivre.external;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("external/services")
public class ExternalServicesController {
    @PersistenceContext
    private EntityManager em;

    @PostMapping("nf")
    @Transactional
    public String nf(@RequestBody @Valid SystemReportNfRequest request) throws InterruptedException {
        Nf transactionReport = request.toModel(em);
        em.persist(transactionReport);
        Thread.sleep(150);
        return transactionReport.toString();
    }

    @PostMapping("ranking")
    @Transactional
    public String ranking(@RequestBody @Valid SystemReportRankingRequest request) throws InterruptedException {
        Ranking transactionReport = request.toModel(em);
        em.persist(transactionReport);
        Thread.sleep(150);
        return transactionReport.toString();
    }
}
