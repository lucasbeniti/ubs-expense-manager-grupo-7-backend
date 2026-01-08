package com.empresa.projeto.gestao_ubs.Service.impl;

import com.empresa.projeto.gestao_ubs.Dto.Currency.*;
import com.empresa.projeto.gestao_ubs.Entity.Currency;
import com.empresa.projeto.gestao_ubs.Exception.ResourceNotFoundException;
import com.empresa.projeto.gestao_ubs.Mapper.CurrencyMapper;
import com.empresa.projeto.gestao_ubs.Repository.CurrencyRepository;
import com.empresa.projeto.gestao_ubs.Service.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Override
    public CurrencyResponseDto createCurrency(CurrencyCreateDto dto) {
        Currency currency = CurrencyMapper.toEntity(dto);
        Currency saved = currencyRepository.save(currency);

        return CurrencyMapper.toResponseDto(saved);
    }

    @Override
    public CurrencyResponseDto getCurrencyById(Long currencyId) {
        Currency currency = currencyRepository.findById(currencyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Currency with given id: " + currencyId + " does not exist"
                        )
                );

        return CurrencyMapper.toResponseDto(currency);
    }

    @Override
    public List<CurrencyResponseDto> getAllCurrencies() {
        return currencyRepository.findAll()
                .stream()
                .map(CurrencyMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CurrencyResponseDto updateCurrency(Long currencyId, CurrencyUpdateDto dto) {
        Currency currency = currencyRepository.findById(currencyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Currency with given id: " + currencyId + " does not exist"
                        )
                );

        CurrencyMapper.updateEntity(currency, dto);
        Currency updated = currencyRepository.save(currency);

        return CurrencyMapper.toResponseDto(updated);
    }

    @Override
    public void deleteCurrency(Long currencyId) {
        Currency currency = currencyRepository.findById(currencyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Currency with given id: " + currencyId + " does not exist"
                        )
                );

        currencyRepository.delete(currency);
    }
}
