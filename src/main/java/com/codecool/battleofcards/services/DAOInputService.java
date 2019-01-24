package com.codecool.battleofcards.services;

import java.util.List;

import com.codecool.battleofcards.dao.CardDAO;
import com.codecool.battleofcards.dao.DAOException;

public class DAOInputService {
    private CardDAO cardDAO;

    public DAOInputService(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    public void addNewCard(String name, List<Integer> attributesToAdd) {
        for (int i = 0; i < attributesToAdd.size(); i++) {
            if (attributesToAdd.get(i) < 0) {
                attributesToAdd.set(i, 0);
            } else if (attributesToAdd.get(i) > 100) {
                attributesToAdd.set(i, 100);
            }
        }

        try {
            cardDAO.insertNew(name, attributesToAdd);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public void editCard(int dbCardId, List<Integer> attributesToEdit) {

        try {
            Card card = cardDAO.getCardById(dbCardId);
            List<Integer> attrs = card.getAttributeList();

            for (int i = 0; i < attributesToEdit.size(); i++) {
                if (attributesToEdit.get(i) < 0) {
                    attributesToEdit.set(i, attrs.get(i));
                }
            }

            cardDAO.update(dbCardId, attributesToEdit);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

}