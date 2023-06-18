/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public abstract class cdDAO {

    protected int CATALOG_SIZE = 10;
    protected ArrayList<CD> catalog;
    protected final String FILE_NAME = "CDdata/CDdata.txt";

    public abstract void getAllCD();

    public abstract CD getCD(String ID);

    public abstract void findCDbyTitle();

    public abstract void addCD();

    public abstract void updateCD();

    public abstract void deleteCDbyTitle();
}
