package com.example.demo.services;

import com.example.demo.entities.Promotion;
import com.example.demo.enums.DiscountType;
import com.example.demo.exceptions.AlReadyExistException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class PromotionService implements IPromotionService {
    @Autowired
    private PromotionRepository promotionRepository ;

    @Override
    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion getPromotionById(Long promotionId) throws NotFoundException {
        return promotionRepository.findById(promotionId)
                .orElseThrow(()->new NotFoundException("Promotion not found !"));
    }

    @Override
    public Promotion createPromotion(Promotion promotion) throws AlReadyExistException {
        List<Promotion> promotions = promotionRepository.findAll();
        for (Promotion promo : promotions) {
            if (Objects.equals(promo.getDiscountValue(), promotion.getDiscountValue())
                    && Objects.equals(promo.getDiscountType(), promotion.getDiscountType())) {
                throw new AlReadyExistException(
                        "Promotion with discount value = "
                                + promotion.getDiscountValue()
                                + " already exists!"
                );
            }
            if (promo.getName().equalsIgnoreCase(promotion.getName())) {
                throw new AlReadyExistException(
                        "Promotion with name = "
                                + promotion.getName()
                                + " already exists!"
                );
            }
            if (Objects.equals(promo.getStartDate(), promotion.getStartDate())
                    && Objects.equals(promo.getEndDate(), promotion.getEndDate())) {

                throw new AlReadyExistException(
                        "A promotion with the same start and end dates already exists!"
                );
            }
        }
        return promotionRepository.save(promotion);
    }

    @Override
    public void deletePromotion(Long promotionId) throws NotFoundException {

        if (!promotionRepository.existsById(promotionId)) {
            throw new NotFoundException(
                    "Promotion with ID = " + promotionId + " not found!"
            );
        }

        promotionRepository.deleteById(promotionId);
    }

    @Override
    public Promotion updatePromotion(Long promotionId, Promotion promotion) throws NotFoundException, AlReadyExistException {
        Promotion existingPromotion = promotionRepository.findById(promotionId)
                .orElseThrow(() -> new NotFoundException(
                        "Promotion with ID = " + promotionId + " not found!"
                ));

        boolean nameExists = promotionRepository.findAll().stream()
                .anyMatch(p ->
                        !p.getId().equals(promotionId)
                                && p.getName().equalsIgnoreCase(promotion.getName())
                );

        if (nameExists) {
            throw new AlReadyExistException(
                    "Promotion with name = " + promotion.getName() + " already exists!"
            );
        }

        if (promotion.getDiscountValue() == null
                || promotion.getDiscountValue() <= 0) {
            throw new IllegalArgumentException("Discount value must be greater than zero!");
        }


        if (promotion.getDiscountType() == DiscountType.PERCENTAGE
                && promotion.getDiscountValue() > 100) {
            throw new IllegalArgumentException("Discount percentage cannot exceed 100%!");
        }

        if (promotion.getStartDate() == null || promotion.getEndDate() == null
                || !promotion.getEndDate().isAfter(promotion.getStartDate())) {
            throw new IllegalArgumentException("End date must be after start date!");
        }

        existingPromotion.setName(promotion.getName());
        existingPromotion.setDescription(promotion.getDescription());
        existingPromotion.setDiscountType(promotion.getDiscountType());
        existingPromotion.setDiscountValue(promotion.getDiscountValue());
        existingPromotion.setStartDate(promotion.getStartDate());
        existingPromotion.setEndDate(promotion.getEndDate());
        existingPromotion.setActive(promotion.getActive());

        return promotionRepository.save(existingPromotion);
    }

    @Override
    public List<Promotion> getPromotionsActive() {
        return promotionRepository.findActivePromotions();
    }
}
