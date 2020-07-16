/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class LimiteNumeros extends DocumentFilter
{   
    private boolean isValid(String testText) {
            if (testText.length() > 4) {
               return false;
            }
            if (testText.isEmpty()) {
               return true;
            }
            int intValue = 0;
            try {
               intValue = Integer.parseInt(testText.trim());
            } catch (NumberFormatException e) {
               return false;
            }
            if (intValue < 0 || intValue > 9999) {
               return false;
            }
            return true; 
         }
    
    @Override
    
    public void insertString(FilterBypass fb, int offset, String text,
               AttributeSet attr) throws BadLocationException {
            StringBuilder sb = new StringBuilder();
            sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
            sb.insert(offset, text);
            if (isValid(sb.toString())) {
               super.insertString(fb, offset, text, attr);
            }
         }

    @Override
  public void replace(FilterBypass fb, int offset, int length,
               String text, AttributeSet attrs) throws BadLocationException {
            StringBuilder sb = new StringBuilder();
            sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
            int end = offset + length;
            sb.replace(offset, end, text);
            if (isValid(sb.toString())) {
               super.replace(fb, offset, length, text, attrs);
            }
         }
    public void remove(FilterBypass fb, int offset, int length)
               throws BadLocationException {
            StringBuilder sb = new StringBuilder();
            sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
            int end = offset + length;
            sb.delete(offset, end);
            if (isValid(sb.toString())) {
               super.remove(fb, offset, length);
            }
         }
      
}