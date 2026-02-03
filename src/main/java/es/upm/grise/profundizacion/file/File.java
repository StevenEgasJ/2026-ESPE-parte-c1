package es.upm.grise.profundizacion.file;

import java.util.List;
import java.util.ArrayList;

public class File {

    private FileType type;
    private List<Character> content;

	/*
	 * Constructor
	 */
    public File() {
        // content must be empty but not null
        this.content = new ArrayList<Character>();
    }

	/*
	 * Method to code / test
	 */
    public void addProperty(char[] newcontent) {

        if (newcontent == null) {
            throw new InvalidContentException("Content is null");
        }
        if (this.type == FileType.IMAGE) {
            throw new WrongFileTypeException("File is of type IMAGE");
        }
        if (this.content == null) {
            this.content = new ArrayList<Character>();
        }
        for (char c : newcontent) {
            this.content.add(c);
        }

    }

	/*
	 * Method to code / test
	 */
    public long getCRC32() {
    
        if (this.content == null || this.content.isEmpty()) {
            return 0L;
        }

        byte[] bytes;

        if (this.type == FileType.IMAGE) {
            // For IMAGE files, only the least significant byte (LSB) of each char is used
            bytes = new byte[this.content.size()];
            for (int i = 0; i < this.content.size(); i++) {
                char c = this.content.get(i);
                bytes[i] = (byte) (c & 0x00FF);
            }
        } else {
            // For PROPERTY (or unspecified) files, use UTF-16 representation (MSB then LSB)
            bytes = new byte[this.content.size() * 2];
            for (int i = 0; i < this.content.size(); i++) {
                char c = this.content.get(i);
                byte msb = (byte) ((c >>> 8) & 0xFF);
                byte lsb = (byte) (c & 0x00FF);
                bytes[2 * i] = msb;
                bytes[2 * i + 1] = lsb;
            }
        }

        FileUtils fu = new FileUtils();
        return fu.calculateCRC32(bytes);
        
    }
    
    
	/*
	 * Setters/getters
	 */
    public void setType(FileType type) {
    	
    	this.type = type;
    	
    }
    
    public List<Character> getContent() {
    	
    	return content;
    	
    }
    
}
