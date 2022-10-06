package pl.byteit.volvodemo.taxcalculator.policy;

import org.springframework.data.jpa.repository.JpaRepository;

interface DateTaxPolicyRepository extends JpaRepository<DateTaxPolicyEntity, Long> {
}
