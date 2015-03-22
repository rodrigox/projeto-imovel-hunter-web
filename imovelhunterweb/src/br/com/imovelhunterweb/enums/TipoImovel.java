package br.com.imovelhunterweb.enums;

public enum TipoImovel {
	CASA 				("Casa"), 
	APARTAMENTO 		("Apartamento"), 
	APARTAMENTO_DUPLEX 	("Apartamento Duplex"), 
	TERRENO 			("Terreno"), 
	AREA 				("Área"), 
	BARRACO	 			("Barraco"),
	CHACARA  			("Chácara"),
	COBERTURA  			("Cobertura"),
	FLAT  				("Flat"),
	GALPAO  			("Galpão"),
	KITNET  			("Kitnet"),
	LOFT  				("Loft"),
	PREDIO  			("Prédio"),
	SALA  				("Sala"),
	SALAO  				("Salão"),
	SITIO  				("Sítio");
	
	private TipoImovel(String nome){
		this.nome = nome;
	}
	
	private final String nome;

	public String getNome() {
		return nome;
	}
	
}
