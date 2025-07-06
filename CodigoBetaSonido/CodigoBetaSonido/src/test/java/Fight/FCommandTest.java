package Fight;

import org.junit.jupiter.api.*;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;
class FCommandTest {
    private FCommand command;

    @BeforeEach
    void setUp() {
        command = new FCommand();
    }

    @AfterEach
    void tearDown() {
        command.state = FCommand.ACTIVATED;
        assertEquals(FCommand.ACTIVATED,command.state);
    }

    @Test
    void testAddButton() {
        FButton button = new FButton();
        button.setText("Test Button");
        command.addButton(button);
        assertTrue(command.getButtons().contains(button));
    }
    @Test
    void testSetConsole(){
        command.createConsole();
        command.setConsoleText("Test Console");
        assertNotNull(command.getConsole());
        assertEquals("Test Console",command.getConsole().getText());
    }
    @Test
    void testSetConfirmKey(){
        int testKey = KeyEvent.VK_ENTER;
        command.setConfirmKey(testKey);
        assertEquals(testKey,command.confirmKey);
    }
    @Test
    void testGetSelectedIndex(){
        FButton button1 = new FButton();
        FButton button2 = new FButton();
        command.addButton(button1);
        command.addButton(button2);

        assertEquals(0, command.getSelectedIndex());
    }
    @Test
    void testCambioEstado(){
        command.state = FCommand.ACTIVATED;
        command.state = FCommand.DESACTIVATED;
        assertEquals(FCommand.DESACTIVATED,command.state);
    }
    @Test
    void testMultiplesBotonos() {
        FButton button1 = new FButton();
        FButton button2 = new FButton();
        FButton button3 = new FButton();

        command.addButton(button1);
        command.addButton(button2);
        command.addButton(button3);

        assertEquals(3, command.getButtons().size());
    }
    @Test
    void testEstadoOnlyConfirm() {
        FButton button = new FButton();
        command.addButton(button);
        command.state = FCommand.ONLYCONFIRM;
        assertEquals(FCommand.ONLYCONFIRM, command.state);
        assertTrue(command.state == FCommand.ONLYCONFIRM);
    }

    @Test
    void testLimpiarBotones() {

        FButton button1 = new FButton();
        FButton button2 = new FButton();
        command.addButton(button1);
        command.addButton(button2);
        command.getButtons().clear();
        command.removeAll();
        assertEquals(0, command.getButtons().size(), "El arreglo de botones debe estar vacio" );
        assertEquals(0, command.getComponentCount(), "No deberían quedar componentes visuales");

    }
}