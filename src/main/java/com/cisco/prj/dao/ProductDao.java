/**
 * 
 */
package com.cisco.prj.dao;

/**
 * @author kbiyani
 *
 */

import java.util.List;

import com.cisco.prj.entity.Product;

public interface ProductDao {
	
	List<Product> getProducts() throws DaoException;

    void addProduct(Product p) throws DaoException;

    Product getProduct(int id) throws DaoException;
	
}
