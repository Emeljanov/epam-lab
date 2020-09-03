package com.epam.winterjavalab.beans;

import com.epam.winterjavalab.beans.enums.SortType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Settings {


    private LocalDate dateFrom;
    private LocalDate dateTo;
    private ShowFor showFor;
    private SortType sortType;
    private List<String> useDepartsment;

    public Settings() {

    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public void setShowFor(ShowFor showFor) {
        this.showFor = showFor;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public void setUseDepartsment(List<String> useDepartsment) {
        this.useDepartsment = useDepartsment;
    }

    public LocalDate getDateFrom() {
        Optional<LocalDate> optionalDateFrom = Optional.ofNullable(dateFrom);
        return optionalDateFrom.orElseGet(() -> LocalDate.of(1000, 01, 01));
    }

    public LocalDate getDateTo() {
        Optional<LocalDate> optionalDateTo = Optional.ofNullable(dateTo);
        return optionalDateTo.orElseGet(() -> LocalDate.of(3000, 01, 01));
    }

    public ShowFor getShowFor() {
        return showFor;
    }

    public SortType getSortType() {
        return sortType;
    }

    public List<String> getUseDepartsment() {
        return useDepartsment;
    }
}
