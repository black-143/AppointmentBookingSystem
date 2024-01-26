package com.pravallika.assessment.service;

import com.pravallika.assessment.model.SaloonItem;
import com.pravallika.assessment.repository.SaloonItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaloonItemService {

    @Autowired
    private SaloonItemRepository saloonItemRepository;

    public SaloonItem addItem(SaloonItem saloonItem){
        return saloonItemRepository.save(saloonItem);
    }

    public SaloonItem updateItem(SaloonItem saloonItem){
        List<SaloonItem> saloonItems = saloonItemRepository.findByCategory(saloonItem.getCategory());
        if(!saloonItems.isEmpty()){
            saloonItem.setId(saloonItems.get(0).getId());
            saloonItem.setAppointments(saloonItems.get(0).getAppointments());
            saloonItemRepository.save(saloonItem);
        }
        return saloonItem;
    }

    public SaloonItem deleteItem(String category){

        SaloonItem item = saloonItemRepository.findByCategory(category).get(0);
        saloonItemRepository.deleteByCategory(category);
        return item;
    }

    public SaloonItem findByCategory(String category){
        return saloonItemRepository.findByCategory(category).get(0);
    }

}
