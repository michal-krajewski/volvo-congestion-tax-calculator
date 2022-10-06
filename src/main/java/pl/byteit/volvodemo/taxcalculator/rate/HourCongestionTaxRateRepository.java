package pl.byteit.volvodemo.taxcalculator.rate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HourCongestionTaxRateRepository extends JpaRepository<HourCongestionTaxRateEntity, Long> {
}
