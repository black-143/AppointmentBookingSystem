package com.pravallika.assessment.repository;

import com.pravallika.assessment.model.SaloonItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface SaloonItemRepository extends JpaRepository<SaloonItem,Integer> {
    public List<SaloonItem> findByCategory(String category);
    public int deleteByCategory(String category);
}
