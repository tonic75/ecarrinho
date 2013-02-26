package br.com.neolog.ecarrinho.util;

import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class NumberField extends JTextField {  
	private static final long serialVersionUID = 1L;

	public NumberField() {  
        this( null );  
    }  
  
    public NumberField( String text ) {  
        super( text );  
        setDocument( new PlainDocument() {  
			private static final long serialVersionUID = 1L;

		@Override  
            public void insertString( int offs, String str, javax.swing.text.AttributeSet a ) throws BadLocationException {  
                //normalmente apenas uma letra é inserida por vez,  
                //mas fazendo assim também previne caaso o usuário  
                //cole algum texto  
                for( int i = 0; i < str.length(); i++ )  
                    if( Character.isDigit( str.charAt( i ) ) == false )  
                        return;  
  
                super.insertString( offs, str, a );  
            }
        } );  
    }  
}  