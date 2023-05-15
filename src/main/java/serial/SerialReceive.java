package serial;
import com.fazecast.jSerialComm.SerialPort;

public class SerialReceive {
    public static byte readByteFromSerialPort() {
        // Find the serial port with the Arduino connected
        SerialPort[] ports = SerialPort.getCommPorts();
        SerialPort port = null;
        for (SerialPort p : ports) {
            if (p.getDescriptivePortName().contains("Arduino")) {
                port = p;
                break;
            }
        }

        if (port != null) {
            // Open the serial port
            port.openPort();

            // Read a byte from the serial port
            byte[] buffer = new byte[1];
            int numRead = port.readBytes(buffer, buffer.length);

            if (numRead == 1) {
                // Byte was successfully read
                byte b = buffer[0];
                System.out.println("Received byte: " + b);

                // Close the serial port
                port.closePort();

                return b;
            } else {
                // Error reading byte
                System.err.println("Error reading byte from serial port");

                // Close the serial port
                port.closePort();

                return -1;
            }
        } else {
            System.err.println("Could not find Arduino connected to serial port");

            return -1;
        }
    }
}

