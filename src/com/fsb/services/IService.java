package com.fsb.services;

import java.util.List;

public interface IService<T> {
    public void ajouterPersonne(T t);
    public void supprimerPersonne(T t);
    public void modifierPersonne(T t);
    public List<T> getAllUsers();
}
