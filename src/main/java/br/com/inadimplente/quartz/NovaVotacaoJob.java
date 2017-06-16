package br.com.inadimplente.quartz;

import br.com.inadimplente.votacao.VotacaoBusiness;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class NovaVotacaoJob implements Job {

    @Inject
    private VotacaoBusiness votacaoBusiness;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            votacaoBusiness.createNovaVotacao();
        } catch (Exception e) {
            throw new JobExecutionException("Falha ao iniciar nova votação", e);
        }
    }
}
