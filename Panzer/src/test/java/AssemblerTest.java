import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import panzer.contracts.*;
import panzer.models.miscellaneous.VehicleAssembler;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class AssemblerTest {
    private final String ARSENAL_MODEL = "X-19";
    private final double ARSENAL_WEIGHT = 20;
    private final BigDecimal ARSENAL_PRICE = new BigDecimal(30);
    private final int ATTACK_MODIFIER = 50;

    private final String ENDURANCE_MODEL = "Endurance-19";
    private final double ENDURANCE_WEIGHT = 30;
    private final BigDecimal ENDURANCE_PRICE = new BigDecimal(40);
    private final int HIT_POINTS_MODIFIER = 50;

    private final String SHELL_MODEL = "Shell-19";
    private final double SHELL_WEIGHT = 30;
    private final BigDecimal SHELL_PRICE = new BigDecimal(40);
    private final int DEFENSE_MODIFIER = 50;


    private Assembler assembler;

    @Before
    public void initializeObj() {
        this.assembler = new VehicleAssembler();
    }

    @Test
    public void checkIfTotalWeightIsCorrect() {
        Part arsenalPart = Mockito.mock(AttackModifyingPart.class);
        Part endurancePart = Mockito.mock(HitPointsModifyingPart.class);
        Part shellPart = Mockito.mock(DefenseModifyingPart.class);

        this.assembler.addArsenalPart(arsenalPart);
        this.assembler.addEndurancePart(endurancePart);
        this.assembler.addShellPart(shellPart);

        Mockito.when(arsenalPart.getWeight()).thenReturn(ARSENAL_WEIGHT);
        Mockito.when(endurancePart.getWeight()).thenReturn(ENDURANCE_WEIGHT);
        Mockito.when(shellPart.getWeight()).thenReturn(SHELL_WEIGHT);


        Assert.assertEquals("Wrong weight", 80, this.assembler.getTotalWeight(), 0.0001);
    }

    @Test
    public void checkIfTotalPriceIsCorrect() {
        Part arsenalPart = Mockito.mock(AttackModifyingPart.class);
        Part endurancePart = Mockito.mock(HitPointsModifyingPart.class);
        Part shellPart = Mockito.mock(DefenseModifyingPart.class);

        this.assembler.addArsenalPart(arsenalPart);
        this.assembler.addEndurancePart(endurancePart);
        this.assembler.addShellPart(shellPart);

        Mockito.when(arsenalPart.getPrice()).thenReturn(ARSENAL_PRICE);
        Mockito.when(endurancePart.getPrice()).thenReturn(ENDURANCE_PRICE);
        Mockito.when(shellPart.getPrice()).thenReturn(SHELL_PRICE);

        Assert.assertEquals(new BigDecimal(110), this.assembler.getTotalPrice());
    }

    @Test
    public void checkIfTotalAttackModificationIsCorrect() {
        Part arsenalPart = Mockito.mock(AttackModifyingPart.class);
        Part arsenalPart2 = Mockito.mock(AttackModifyingPart.class);

        this.assembler.addArsenalPart(arsenalPart);
        this.assembler.addArsenalPart(arsenalPart2);

        Mockito.when(((AttackModifyingPart) arsenalPart).getAttackModifier()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(((AttackModifyingPart) arsenalPart2).getAttackModifier()).thenReturn(Integer.MAX_VALUE);


        Assert.assertEquals(2L * Integer.MAX_VALUE, this.assembler.getTotalAttackModification());
    }

    @Test
    public void checkIfTotalDefenceModificationIsCorrect() {
        Part shellPart = Mockito.mock(DefenseModifyingPart.class);
        Part shellPart2 = Mockito.mock(DefenseModifyingPart.class);

        this.assembler.addShellPart(shellPart);
        this.assembler.addShellPart(shellPart2);

        Mockito.when(((DefenseModifyingPart) shellPart).getDefenseModifier()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(((DefenseModifyingPart) shellPart2).getDefenseModifier()).thenReturn(Integer.MAX_VALUE);


        Assert.assertEquals(2L * Integer.MAX_VALUE, this.assembler.getTotalDefenseModification());
    }

    @Test
    public void checkIfTotalHitPointsModificationIsCorrect() {
        Part endurancePart = Mockito.mock(HitPointsModifyingPart.class);
        Part endurancePart2 = Mockito.mock(HitPointsModifyingPart.class);

        this.assembler.addEndurancePart(endurancePart);
        this.assembler.addEndurancePart(endurancePart2);

        Mockito.when(((HitPointsModifyingPart) endurancePart).getHitPointsModifier()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(((HitPointsModifyingPart) endurancePart2).getHitPointsModifier()).thenReturn(Integer.MAX_VALUE);


        Assert.assertEquals(2L * Integer.MAX_VALUE, this.assembler.getTotalHitPointModification());
    }

    @Test
    public void checkIfArsenalPartsAddedCorrectly(){
        Part arsenalPart = Mockito.mock(AttackModifyingPart.class);
        Part arsenalPart2 = Mockito.mock(AttackModifyingPart.class);

        List<AttackModifyingPart> arsenalParts = getArsenalParts();
        arsenalParts.add((AttackModifyingPart) arsenalPart);
        arsenalParts.add((AttackModifyingPart) arsenalPart2);
        arsenalParts = getArsenalParts();

        Assert.assertEquals(2,arsenalParts.size());
    }

    @Test
    public void checkIfShellPartsAddedCorrectly(){
        Part shellPart = Mockito.mock(DefenseModifyingPart.class);
        Part shellPart2 = Mockito.mock(DefenseModifyingPart.class);

        List<DefenseModifyingPart> shellParts = getShellParts();
        shellParts.add((DefenseModifyingPart) shellPart);
        shellParts.add((DefenseModifyingPart) shellPart2);
        shellParts = getShellParts();

        Assert.assertEquals(2,shellParts.size());

    }

    @Test
    public void checkIfEndurancePartsAddedCorrectly(){
        Part endurancePart = Mockito.mock(HitPointsModifyingPart.class);
        Part endurancePart2 = Mockito.mock(HitPointsModifyingPart.class);

        List<HitPointsModifyingPart> enduranceParts = getEndurancePart();
        enduranceParts.add((HitPointsModifyingPart) endurancePart);
        enduranceParts.add((HitPointsModifyingPart) endurancePart2);
        enduranceParts = getEndurancePart();

        Assert.assertEquals(2,enduranceParts.size());
    }



    private List<AttackModifyingPart> getArsenalParts() {
        try {
            Field field = this.assembler.getClass().getDeclaredField("arsenalParts");
            field.setAccessible(true);
            return (List<AttackModifyingPart>) field.get(this.assembler);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<DefenseModifyingPart> getShellParts() {
        try {
            Field field = this.assembler.getClass().getDeclaredField("shellParts");
            field.setAccessible(true);
            return (List<DefenseModifyingPart>) field.get(this.assembler);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<HitPointsModifyingPart> getEndurancePart() {
        try {
            Field field = this.assembler.getClass().getDeclaredField("enduranceParts");
            field.setAccessible(true);
            return (List<HitPointsModifyingPart>) field.get(this.assembler);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
