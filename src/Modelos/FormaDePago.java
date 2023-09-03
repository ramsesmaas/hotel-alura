/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author maast
 */
public enum FormaDePago {
    TARJETA_DE_CREDITO("Tarjeta de credito"),
    TARJETA_DE_DEBITO("Tarjeta de debito"),
    DINERO_EN_EFECTIVO("Dinero en efectivo");
    
    private String formaDePago;
    
    private FormaDePago (String formaDepago){
		this.formaDePago = formaDepago;
		
	}

	public String getFormaDePago() {
		return formaDePago;
	}
}
