package study.racingcar.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cars implements Iterable<Car> {
    private List<Car> cars;

    public Cars() {
        this.cars = new ArrayList<>();
    }

    public Cars(List<String> carNames) {
        if (Objects.isNull(carNames) || carNames.isEmpty()) {
            throw new IllegalArgumentException("null을 차로 입력할 수 없습니다.");
        }

        this.cars = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    public void add(Car car) {
        cars.add(car);
    }

    public int size() {
        return cars.size();
    }

    public int getMaxPosition() {
        return Collections
                .max(cars, Comparator.comparing(Car::getPosition))
                .getPosition();
    }

    public Cars getByPosition(int position) {
        Cars matchedCars = new Cars();
        cars.stream()
                .filter(car -> car.isPositionEqualTo(position))
                .forEach(car -> matchedCars
                        .add(new Car(car.getName(), car.getPosition()))
                );
        return matchedCars;
    }

    public Stream<Car> stream() {
        return cars.stream();
    }

    @Override public Iterator<Car> iterator() {
        return cars.iterator();
    }
}
