package edu.hanover.schedulevisualizer.core.entity;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Class that implements the interface Timeslot
 */
public class HCTimeSlot implements TimeSlot {
    private final List<Weekday> weekdayList;
    public int slotnum;

     public HCTimeSlot(final List<Weekday> weekdayList, final int slotnum){
        this.weekdayList = weekdayList;
        this.slotnum = slotnum;
    }

    @Override
    public String getId() {
        String weekdaysString = "";
        for (final Weekday w : weekdayList) { weekdaysString += w; }
        return weekdaysString + "-" + slotnum;
    }

    public String getAbbrId() {
        StringBuilder weekdaysString = new StringBuilder();
        for (final Weekday w : weekdayList) { weekdaysString.append(w.toShortString()); }
        return weekdaysString + "-" + slotnum;
    }

    @SuppressWarnings("PMD.CyclomaticComplexity")
    public DayTime getStartTime() {
        return switch (slotnum) {
            case 1 -> new DayTime(8, 0);
            case 2 -> new DayTime(9, 20);
            case 3 -> new DayTime(10, 40);
            case 4 -> new DayTime(12, 0);
            case 5 -> new DayTime(1, 20);
            case 6 -> new DayTime(2, 40);
            case 7 -> new DayTime(8, 0);
            case 8 -> new DayTime(10, 0);
            case 9 -> new DayTime(12, 20);
            case 10->  new DayTime(2, 15);
            default -> throw new RuntimeException("Not a Valid startTime for this class!");
        };
    }

    @SuppressWarnings("PMD.CyclomaticComplexity")
    public DayTime getEndTime() {
        return switch (slotnum) {
            case 1 -> new DayTime(9, 10);
            case 2 -> new DayTime(10, 30);
            case 3 -> new DayTime(11, 50);
            case 4 -> new DayTime(1, 10);
            case 5 -> new DayTime(2, 30);
            case 6 -> new DayTime(3, 50);
            case 7 -> new DayTime(9, 45);
            case 8 -> new DayTime(11, 45);
            case 9 -> new DayTime(2, 5);
            case 10 -> new DayTime(4, 0);
            default -> throw new RuntimeException("Not a Valid endTime for this class!");
        };
    }
    public List<Weekday> getWeekdayList(){
        return weekdayList;
    }

    public boolean overlaps(final TimeSlot otherSlot) {
        final HCTimeSlot hcOtherSlot = (HCTimeSlot) otherSlot;
        return hcOtherSlot.slotnum == slotnum &&
                hasDaysInCommonWith(hcOtherSlot);

    }

    public void ifAssignedSlotNumberDo(final Consumer<Integer> f) {
        f.accept(slotnum);
    }

    public void ifUnAssignedSlotNumberDo(Runnable f){ //NOPMD
        // does nothing
    }


    public void ifUnassignedSlotDo(final Runnable runnable) {
    }

    public <T> T computeWithSlot(BiFunction<List<Weekday>, Integer, T> f1, Supplier<T> f2) {
        return f1.apply(weekdayList, slotnum);
    }

    private boolean hasDaysInCommonWith(final HCTimeSlot hcOtherSlot) {
        for(final Weekday day1 : weekdayList) {
            if (hcOtherSlot.weekdayList.contains(day1)) return true;
        }
        return false;
    }

    public String toString() {
        return this.getStartTime().toString() + " - " + this.getEndTime().toString();
    }


}
