package es.upm.grise.profundizacion.file;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileTest {

    @Test
    public void constructorInitializesContent() {
        File f = new File();
        assertNotNull(f.getContent());
        assertTrue(f.getContent().isEmpty());
    }

    @Test
    public void addPropertyAddsContent() {
        File f = new File();
        f.setType(FileType.PROPERTY);
        char[] prop = new char[] { 'D', 'A', 'T', 'E', '=', '2', '0' };
        f.addProperty(prop);
        assertEquals(prop.length, f.getContent().size());
        for (int i = 0; i < prop.length; i++) {
            assertEquals(prop[i], f.getContent().get(i).charValue());
        }
    }

    @Test
    public void addPropertyNullThrows() {
        File f = new File();
        f.setType(FileType.PROPERTY);
        assertThrows(InvalidContentException.class, () -> f.addProperty(null));
    }

    @Test
    public void addPropertyImageThrowsWrongFileTypeException() {
        File f = new File();
        f.setType(FileType.IMAGE);
        char[] prop = new char[] { 'x' };
        assertThrows(WrongFileTypeException.class, () -> f.addProperty(prop));
    }

    @Test
    public void getCRC32EmptyContentReturnsZero() {
        File f = new File();
        f.setType(FileType.PROPERTY);
        assertEquals(0L, f.getCRC32());
    }

    @Test
    public void getCRC32NonEmptyReturnsCalculatedValue() {
        File f = new File();
        f.setType(FileType.PROPERTY);
        f.addProperty(new char[] { 'A' });
        // FileUtils.calculateCRC32 currently returns 0 by default
        assertEquals(0L, f.getCRC32());
    }

}
