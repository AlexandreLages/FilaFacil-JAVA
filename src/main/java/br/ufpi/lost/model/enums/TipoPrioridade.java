package br.ufpi.lost.model.enums;

public enum TipoPrioridade {
	
	PRIORITARIO("A preferência é sempre da prioridade"), MEIO("Alterna sempre entre o prioritário e não prioritário ");
	
	private TipoPrioridade(String descricao) {
		this.setDescricao(descricao);
	}

	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


}
