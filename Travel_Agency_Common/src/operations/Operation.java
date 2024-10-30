/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package operations;

import java.io.Serializable;

/**
 *
 * @author natalija
 */
public enum Operation implements Serializable {
    LOGIN,
    KRAJ_RADA,
    KRAJ_RADA_LOGIN,
    
    UCITAJ_KORISNIKE,
    OBRISI_KORISNIKA,
    DODAJ_KORISNIKA,
    AZURIRAJ_KORISNIKA,
    VRATI_KORISNIKE_PO_IMENU,
    
    UCITAJ_DESTINACIJE,
    
    UCITAJ_ARANZMANE,
    OBRISI_ARANZMAN,
    DODAJ_ARANZMAN,
    AZURIRAJ_ARANZMANE,
    VRATI_ARANZMANE_PO_TIPU,
    
    DODAJ_REZERVACIJU,
    UCITAJ_REZERVACIJE,
    VRATI_MAX_ID_REZERVACIJE,
    VRATI_REZERVACIJE_PO_SIFRI,
    AZURIRAJ_REZERVACIJU,
    
     VRATI_MAX_ID_RASPORED_SOBA,
     VRATI_RASPOREDE_SOBA_PO_ID_REZERVACIJE,
     
     VRATI_REZERVACIJE,
     VRATI_RASPOREDE_SOBA_PO_ID_KORISNIKA,
     
    
    
}
