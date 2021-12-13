package com.manudev90.userapi.exception;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class ErrorResponse 
{
    public ErrorResponse(String mensaje, List<String> detalles) {
        super();
        this.mensaje = mensaje;
        this.detalles = detalles;
    }
 
    private String mensaje;
 
    private List<String> detalles;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<String> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<String> detalles) {
		this.detalles = detalles;
	}
 
  
}