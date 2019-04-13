package org.softuni.university.service;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.entities.Quote;
import org.softuni.university.domain.models.service.QuoteServiceModel;
import org.softuni.university.error.QuoteNotFoundException;
import org.softuni.university.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public QuoteServiceImpl(
            QuoteRepository quoteRepository,
            ModelMapper modelMapper
    ) {
        this.quoteRepository = quoteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public QuoteServiceModel addQuote(QuoteServiceModel quoteServiceModel) throws Exception {
        Quote quote = this.modelMapper.map(quoteServiceModel, Quote.class);

        return this.modelMapper.map(this.quoteRepository.saveAndFlush(quote), QuoteServiceModel.class);
    }

    @Override
    public List<QuoteServiceModel> findAllQuotes() {
        return this.quoteRepository.findAll()
                .stream()
                .map(c -> this.modelMapper.map(c, QuoteServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public QuoteServiceModel findQuoteById(String id) {
        Quote quote = this.quoteRepository.findById(id)
                .orElseThrow(() -> new QuoteNotFoundException("QuoteNotFoundException with the given id was not found!"));

        return this.modelMapper.map(quote, QuoteServiceModel.class);
    }

    @Override
    public QuoteServiceModel editQuote(String id, QuoteServiceModel quoteServiceModel) {
        Quote quote = this.quoteRepository.findById(id)
                .orElseThrow(() -> new QuoteNotFoundException("QuoteNotFoundException with the given id was not found!"));

        quoteSets(quoteServiceModel, quote);

        return this.modelMapper.map(this.quoteRepository.saveAndFlush(quote), QuoteServiceModel.class);
    }

    @Override
    public QuoteServiceModel deleteQuote(String id) {
        Quote quote = this.quoteRepository.findById(id)
                .orElseThrow(() -> new QuoteNotFoundException("QuoteNotFoundException with the given id was not found!"));

        this.quoteRepository.delete(quote);

        return this.modelMapper.map(quote, QuoteServiceModel.class);
    }

    private void quoteSets(QuoteServiceModel quoteServiceModel, Quote quote) {
        quote.setText(quoteServiceModel.getText());
        quote.setAuthor(quoteServiceModel.getAuthor());
        quote.setReason(quoteServiceModel.getReason());
        quote.setPlace(quoteServiceModel.getPlace());
        quote.setYear(quoteServiceModel.getYear());
    }
}
