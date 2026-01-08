package com.empresa.projeto.gestao_ubs.Service.impl;

import com.empresa.projeto.gestao_ubs.Dto.CurrencyRate.*;
import com.empresa.projeto.gestao_ubs.Entity.Currency;
import com.empresa.projeto.gestao_ubs.Entity.CurrencyRate;
import com.empresa.projeto.gestao_ubs.Exception.ResourceNotFoundException;
import com.empresa.projeto.gestao_ubs.Mapper.CurrencyRateMapper;
import com.empresa.projeto.gestao_ubs.Repository.CurrencyRateRepository;
import com.empresa.projeto.gestao_ubs.Repository.CurrencyRepository;
import com.empresa.projeto.gestao_ubs.Service.CurrencyRateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CurrencyRateServiceImpl implements CurrencyRateService {

    private final CurrencyRateRepository currencyRateRepository;
    private final CurrencyRepository currencyRepository;

    @Override
    public CurrencyRateResponseDto createCurrencyRate(CurrencyRateCreateDto dto) {
        Currency currency = currencyRepository.findById(dto.getCurrency_id())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Currency with given id: " + dto.getCurrency_id() + " does not exist"
                        )
                );

        CurrencyRate rate = CurrencyRateMapper.toEntity(dto, currency);
        CurrencyRate saved = currencyRateRepository.save(rate);

        return CurrencyRateMapper.toResponseDto(saved);
    }

    @Override
    public CurrencyRateResponseDto getCurrencyRateById(Long currencyRateId) {
        CurrencyRate rate = currencyRateRepository.findById(currencyRateId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "CurrencyRate with given id: " + currencyRateId + " does not exist"
                        )
                );

        return CurrencyRateMapper.toResponseDto(rate);
    }

    @Override
    public List<CurrencyRateResponseDto> getAllCurrencyRates() {
        return currencyRateRepository.findAll()
                .stream()
                .map(CurrencyRateMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CurrencyRateResponseDto updateCurrencyRate(
            Long currencyRateId,
            CurrencyRateUpdateDto dto
    ) {
        CurrencyRate rate = currencyRateRepository.findById(currencyRateId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "CurrencyRate with given id: " + currencyRateId + " does not exist"
                        )
                );

        Currency currency = currencyRepository.findById(dto.getCurrency_id())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Currency with given id: " + dto.getCurrency_id() + " does not exist"
                        )
                );

        CurrencyRateMapper.updateEntity(rate, dto, currency);
        CurrencyRate updated = currencyRateRepository.save(rate);

        return CurrencyRateMapper.toResponseDto(updated);
    }

    @Override
    public void deleteCurrencyRate(Long currencyRateId) {
        CurrencyRate rate = currencyRateRepository.findById(currencyRateId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "CurrencyRate with given id: " + currencyRateId + " does not exist"
                        )
                );

        currencyRateRepository.delete(rate);
    }
}
