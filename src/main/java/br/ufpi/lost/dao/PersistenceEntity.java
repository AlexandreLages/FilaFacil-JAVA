/**
 * 
 */
package br.ufpi.lost.dao;

import java.io.Serializable;

/**
 * @author Werney Ayala
 *
 */
public interface PersistenceEntity extends Serializable {

		/**
		 * @return id
		 */
		Long getId();
		
		/**
		 * @param id
		 */
		void setId(Long id);
	
}
