package zedly.mcpu.avr;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import zedly.mcpu.Instruction;
import zedly.mcpu.avr.ATMega320;

/**
 *
 * @author Dennis
 */
public class ANDI_CBR extends Instruction {

    private final int d, k;
    private final ATMega320 cpu;

    public ANDI_CBR(int opcode, ATMega320 cpu) {
        this.cpu = cpu;
        k = (opcode & 0xF00) >> 4 + (opcode & 0xF);
        d = 16 + (opcode & 0b11110000) >> 4;
    }

    public void run() {
        int and = cpu.readByte(d) & k;
        int status = cpu.readByte(ATMega320.SREG);

        status &= 0b11100001;

        status |= ((and == 0) ? 0x2 : 0x0);
        status |= (((and & 0x80) != 0) ? 0x4 : 0x0);
        status |= (((status & 0x2) != 0) != ((status & 0x4) != 0)) ? 0x10 : 0;

        cpu.writeByte(d, and);
        cpu.writeByte(ATMega320.SREG, status);
    }
}
