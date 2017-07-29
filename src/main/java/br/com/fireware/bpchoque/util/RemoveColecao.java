package br.com.fireware.bpchoque.util;

import java.util.Collection;
import java.util.Iterator;

public class RemoveColecao {
	
	public static void removeOfThis(Collection<?> listaOriginal,  Collection<?> listaRemover) {
	    Object prova = null;
		for (Iterator<?> it = listaOriginal.iterator(); it.hasNext();){
	    	 prova = it.next();
	    	for (Iterator<?> it2 = listaRemover.iterator(); it2.hasNext();){
	    		if (prova == it2.next())
	    			it.remove();
	    	}
	    }
	}

}
