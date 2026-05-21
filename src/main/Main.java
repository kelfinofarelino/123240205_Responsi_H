/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author 123240205
 */
import model.CattyCareModel;
import view.CattyCareView;
import controller.CattyCareController;

public class Main {
    public static void main(String[] args) {
        CattyCareModel model = new CattyCareModel();
        CattyCareView view = new CattyCareView();
        CattyCareController controller = new CattyCareController(model, view);
        
        view.setVisible(true);
    }
}