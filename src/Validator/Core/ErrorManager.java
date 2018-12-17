/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator.Core;

/**
 *
 * @author thinhnh
 */
public interface ErrorManager {

    void addErrorMessage(String attributeName, String message);

    void check();
}
