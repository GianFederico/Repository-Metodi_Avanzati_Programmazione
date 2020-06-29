/*
 * Copyright (C) 2020 pierpaolo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package di.uniba.map.b.lab.collection.esercizi;

import java.util.Map;

/**
 *
 * @author pierpaolo
 */
public class Ordine {
    
    private String id;
    
    private String userId;
    
    private Map<Articolo,Integer> articoli;

    private double costoMerce;

    private double costoSpedizione;

    public Ordine(String id, String userId) {
        this.id = id;
        this.userId = userId;
    }
    
    public Ordine(String id, String userId, Carrello carrello) {
        this.id = id;
        this.userId = userId;
        this.articoli=carrello.getArticoli();
        this.costoMerce=carrello.getCostoMerce();
        this.costoSpedizione=carrello.getCostoSpedizione();
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<Articolo, Integer> getArticoli() {
        return articoli;
    }

    public void setArticoli(Map<Articolo, Integer> articoli) {
        this.articoli = articoli;
    }

    public double getCostoMerce() {
        return costoMerce;
    }

    public void setCostoMerce(double costoMerce) {
        this.costoMerce = costoMerce;
    }

    public double getCostoSpedizione() {
        return costoSpedizione;
    }

    public void setCostoSpedizione(double costoSpedizione) {
        this.costoSpedizione = costoSpedizione;
    }
    
    public double getCostoTotale() {
        return costoMerce + costoSpedizione;
    }

    @Override
    public String toString() {
        return "Ordine{" + "id=" + id + ", costoMerce=" + costoMerce + ", costoSpedizione=" + costoSpedizione+ ", costoTotale=" + getCostoTotale() + '}';
    }
    
    
    
}
