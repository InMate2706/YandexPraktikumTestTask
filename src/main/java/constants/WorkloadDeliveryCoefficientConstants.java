package constants;

import java.util.List;
import java.util.Random;

/**
 * Класс констант для коэффициентов загруженности службы доставки
 */
public class WorkloadDeliveryCoefficientConstants {

    public final static Float DEFAULT_WORKLOAD_COEFFICIENT = 1f;
    public final static Float INCREASED_WORKLOAD_COEFFICIENT = 1.2f;
    public final static Float HIGH_WORKLOAD_COEFFICIENT = 1.4f;
    public final static Float VERY_HIGH_WORKLOAD_COEFFICIENT = 1.6f;

    /**
     * Все доступные значения коэффициентов загруженности службы доставки
     */
    public final static List<Float> ALLOWABLE_WORKLOAD_COEFFICIENT = List.of(DEFAULT_WORKLOAD_COEFFICIENT,
            INCREASED_WORKLOAD_COEFFICIENT, HIGH_WORKLOAD_COEFFICIENT, VERY_HIGH_WORKLOAD_COEFFICIENT);

    /**
     * Получение произвольного значения коэффициента загруженности службы доставки
     */
    public static Float getRandomWorkLoadCoefficient() {
        Random random = new Random();
        return ALLOWABLE_WORKLOAD_COEFFICIENT.get(random.nextInt(ALLOWABLE_WORKLOAD_COEFFICIENT.size() - 1));
    }
}