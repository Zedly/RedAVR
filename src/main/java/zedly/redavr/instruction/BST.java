/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zedly.redavr.instruction;

import zedly.redavr.CPU;

/**
 *
 * @author Dennis
 */
public class BST extends Instruction {

    private final int s, k;
    private final CPU cpu;

    public BST(int opcode, CPU cpu) {
        this.cpu = cpu;
        k = (opcode & 0b111110000) >> 4;
        s = opcode & 0b111;
    }

    public void run() {
        if ((cpu.memory[k] & ~(1 << s)) != 0) {
            cpu.memory[CPU.SREG] |= 0b1000000;
        } else {
            cpu.memory[CPU.SREG] &= 0b10111111;
        }
    }
}