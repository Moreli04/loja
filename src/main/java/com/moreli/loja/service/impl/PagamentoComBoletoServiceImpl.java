package com.moreli.loja.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.moreli.loja.model.PagamentoComBoleto;

@Service
public class PagamentoComBoletoServiceImpl {

	private static int QTD_DIAS_PARA_REALIZAR_PAGAMENTO = 7;
	public void prepararParaInsert(PagamentoComBoleto pagamento) {
		final LocalDate dataVencimento =
				pagamento.getDataPagamento().plusDays(QTD_DIAS_PARA_REALIZAR_PAGAMENTO);
		pagamento.setDataVencimento(dataVencimento);
	}
}
