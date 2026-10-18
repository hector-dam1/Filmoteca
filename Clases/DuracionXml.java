package Clases;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class DuracionXml {

    private String tipo;
    private int valor;

    public DuracionXml() {}

    public DuracionXml(int valor, String tipo) {
        this.valor = valor;
        this.tipo = tipo;
    }

    @XmlAttribute(name = "tipo")
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlValue
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}