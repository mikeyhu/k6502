package net.chompsoftware.k6502.hardware.operations

import io.kotest.matchers.shouldBe
import net.chompsoftware.k6502.hardware.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test


@ExperimentalUnsignedTypes
class MathOperationsTest {

    @Nested
    inner class ExclusiveOr {
        @Test
        fun `Should XOR the accumulator with the immediate value`() {
            val memory = Memory(setupMemory(InstructionSet.eor_i.u, 0xffu))
            val state = CpuState(aRegister = 0x0fu)
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2L,
                    programCounter = 0x02,
                    aRegister = 0xf0u,
                    isNegativeFlag = true
            )
        }
    }

    @Nested
    inner class AddWithCarry {
        @Test
        fun `Should add value to accumulator`() {
            val memory = Memory(setupMemory(InstructionSet.adc_i.u, 0x1u))
            val state = CpuState(aRegister = 0x0fu)
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2,
                    programCounter = 0x02,
                    aRegister = 0x10u,
                    isCarryFlag = false
            )
        }

        @Test
        fun `Should add value to accumulator with carry`() {
            val memory = Memory(setupMemory(InstructionSet.adc_i.u, 0xc4u))
            val state = CpuState(aRegister = 0xc0u)
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2,
                    programCounter = 0x02,
                    aRegister = 0x84u,
                    isCarryFlag = true
            )
        }
    }

    @Nested
    inner class OrWithAccumulator {
        @Test
        fun `Should OR the accumulator with the immediate value`() {
            val memory = Memory(setupMemory(InstructionSet.ora_i.u, 0xf0u))
            val state = CpuState(aRegister = 0x0fu)
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2L,
                    programCounter = 0x02,
                    aRegister = 0xffu,
                    isNegativeFlag = true
            )
        }
    }

    @Nested
    inner class DecrementX {
        @Test
        fun `Should decrement X`() {
            val memory = Memory(setupMemory(InstructionSet.dex.u))
            val state = CpuState(
                    xRegister = 0x5u
            )
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2L,
                    programCounter = 0x01,
                    xRegister = 0x4u
            )
        }
    }

    @Nested
    inner class DecrementY {
        @Test
        fun `Should decrement Y`() {
            val memory = Memory(setupMemory(InstructionSet.dey.u))
            val state = CpuState(
                    yRegister = 0x5u
            )
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2L,
                    programCounter = 0x01,
                    yRegister = 0x4u
            )
        }
    }

    @Nested
    inner class IncrementX {
        @Test
        fun `Should increment X`() {
            val memory = Memory(setupMemory(InstructionSet.inx.u))
            val state = CpuState(
                    xRegister = 0x5u
            )
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2L,
                    programCounter = 0x01,
                    xRegister = 0x6u
            )
        }

        @Test
        fun `Should increment X wrapping around at 0x100u`() {
            val memory = Memory(setupMemory(InstructionSet.inx.u))
            val state = CpuState(
                    xRegister = 0xffu
            )
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2L,
                    programCounter = 0x01,
                    xRegister = 0x0u,
                    isZeroFlag = true
            )
        }
    }

    @Nested
    inner class IncrementY {
        @Test
        fun `Should increment Y`() {
            val memory = Memory(setupMemory(InstructionSet.iny.u))
            val state = CpuState(
                    yRegister = 0x5u
            )
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2L,
                    programCounter = 0x01,
                    yRegister = 0x6u
            )
        }
    }

    @Nested
    inner class Bit {
        @Test
        fun `Should set flags based on ANDing accumulator and value`() {
            val memory = Memory(setupMemory(InstructionSet.bit_z.u, 0xf0u))
            memory[0xf0u] = 0xf0u
            val state = CpuState(
                    aRegister = 0xffu
            )
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 3L,
                    programCounter = 0x02,
                    isNegativeFlag = true,
                    isOverflowFlag = true
            )
        }

        @Test
        fun `Should unset flags based on ANDing accumulator and value`() {
            val memory = Memory(setupMemory(InstructionSet.bit_z.u, 0x00u))
            memory[0xf0u] = 0xf0u
            val state = CpuState(
                    aRegister = 0xffu,
                    isNegativeFlag = true,
                    isOverflowFlag = true
            )
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 3L,
                    programCounter = 0x02,
                    isNegativeFlag = false,
                    isOverflowFlag = false
            )
        }
    }

    @Nested
    inner class ArithmeticShiftLeft {
        @Test
        fun `Should shift the accumulator`() {
            val memory = Memory(setupMemory(InstructionSet.asl_none.u))
            val state = CpuState(
                    aRegister = 0xf2u
            )
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2L,
                    programCounter = 0x01,
                    aRegister = 0xe4u,
                    isNegativeFlag = true,
                    isCarryFlag = true
            )
        }

        @Test
        fun `Should shift the zero page address`() {
            val memory = Memory(setupMemory(InstructionSet.asl_z.u, 0xeeu))
            memory[0xeeu] = 0xf2u
            val state = CpuState()
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 5L,
                    programCounter = 0x02,
                    isNegativeFlag = true,
                    isCarryFlag = true
            )
            memory[0xeeu].toUInt() shouldBe 0xe4u
        }
    }
}