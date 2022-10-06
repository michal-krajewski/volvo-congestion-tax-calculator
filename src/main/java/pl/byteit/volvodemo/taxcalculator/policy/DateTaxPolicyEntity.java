package pl.byteit.volvodemo.taxcalculator.policy;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.function.Function;

@Entity
@Table(name = "date_tax_policies")
class DateTaxPolicyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private DateRangeType type;

	@Column(nullable = false)
	private String expression;

	@Deprecated
	protected DateTaxPolicyEntity() {
	}

	DateTaxPolicyEntity(DateRangeType type, String expression) {
		this.type = type;
		this.expression = expression;
	}

	TaxPolicy toPolicy() {
		return type.mapper.apply(expression);
	}

	enum DateRangeType {
		SINGLE_DAY(expression -> new SingleDateTaxPolicy(LocalDate.parse(expression))),
		DAY_OF_WEEK(expression -> new DayOfWeekTaxPolicy(DayOfWeek.valueOf(expression))),
		DATE_RANGE(expression -> {
			String[] split = expression.split(" - ");
			return new DatesPeriodTaxPolicy(LocalDate.parse(split[0]), LocalDate.parse(split[1]));
		});

		private final Function<String, TaxPolicy> mapper;

		DateRangeType(Function<String, TaxPolicy> mapper) {
			this.mapper = mapper;
		}
	}

}
