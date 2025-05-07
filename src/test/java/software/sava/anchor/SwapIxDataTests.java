package software.sava.anchor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import software.sava.anchor.gen.meteoradlmm.anchor.LbClmmProgram.SwapIxData;
import software.sava.core.encoding.Base58;

import java.util.OptionalInt;

public class SwapIxDataTests {

    @Test
    public void testReadFromByteArray() {
        // Create test data
        byte[] data = new byte[24]; // 8 bytes discriminator + 8 bytes amountIn + 8 bytes minAmountOut
        int offset = 0;
        
        // Set discriminator bytes
        byte[] discriminatorBytes = new byte[] {65, 75, 63, 76, -21, 91, 91, -120}; // SWAP2_DISCRIMINATOR
        System.arraycopy(discriminatorBytes, 0, data, offset, 8);
        offset += 8;
        
        // Set amountIn (1000)
        data[offset] = -24; // 0xE8
        data[offset + 1] = 3; // 0x03
        data[offset + 2] = 0;
        data[offset + 3] = 0;
        data[offset + 4] = 0;
        data[offset + 5] = 0;
        data[offset + 6] = 0;
        data[offset + 7] = 0;
        offset += 8;
        
        // Set minAmountOut (500)
        data[offset] = -12; // 0xF4
        data[offset + 1] = 1; // 0x01
        data[offset + 2] = 0;
        data[offset + 3] = 0;
        data[offset + 4] = 0;
        data[offset + 5] = 0;
        data[offset + 6] = 0;
        data[offset + 7] = 0;

        String rawData = "fx9RHbGFfZAKEVh4sGhgfugZh3hHWH4JzCXFro";
        byte[] decode = Base58.decode(rawData);
        
        // Read the data
        SwapIxData swapIxData = SwapIxData.read(decode, 0);
        
        // Verify the results
        assertNotNull(swapIxData);
        System.out.println(swapIxData.toString());
    }
    
    @Test
    public void testReadFromEmptyByteArray() {
        byte[] emptyData = new byte[0];
        SwapIxData swapIxData = SwapIxData.read(emptyData, 0);
        assertNull(swapIxData);
    }
    
    @Test
    public void testReadFromNullByteArray() {
        SwapIxData swapIxData = SwapIxData.read(null, 0);
        assertNull(swapIxData);
    }
    
    @Test
    public void testReadFromInstruction() {
        // Create test data
        byte[] data = new byte[24];
        int offset = 0;
        
        // Set discriminator bytes
        byte[] discriminatorBytes = new byte[] {65, 75, 63, 76, -21, 91, 91, -120}; // SWAP2_DISCRIMINATOR
        System.arraycopy(discriminatorBytes, 0, data, offset, 8);
        offset += 8;
        
        // Set amountIn (1000)
        data[offset] = -24; // 0xE8
        data[offset + 1] = 3; // 0x03
        data[offset + 2] = 0;
        data[offset + 3] = 0;
        data[offset + 4] = 0;
        data[offset + 5] = 0;
        data[offset + 6] = 0;
        data[offset + 7] = 0;
        offset += 8;
        
        // Set minAmountOut (500)
        data[offset] = -12; // 0xF4
        data[offset + 1] = 1; // 0x01
        data[offset + 2] = 0;
        data[offset + 3] = 0;
        data[offset + 4] = 0;
        data[offset + 5] = 0;
        data[offset + 6] = 0;
        data[offset + 7] = 0;
        
     /*   // Create instruction
        Instruction instruction = new Instruction(data, 0);
        
        // Read the data
        SwapIxData swapIxData = SwapIxData.read(instruction);
        
        // Verify the results
        assertNotNull(swapIxData);
        assertEquals(1000L, swapIxData.amountIn());
        assertEquals(500L, swapIxData.minAmountOut());*/
    }
    
    @Test
    public void testReadWithInvalidData() {
        // Create invalid data (too short)
        byte[] invalidData = new byte[10]; // Less than required 24 bytes
        SwapIxData swapIxData = SwapIxData.read(invalidData, 0);
        assertNull(swapIxData);
    }
} 