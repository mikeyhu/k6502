package net.chompsoftware.k6502.hardware.operations

import io.kotest.matchers.shouldBe
import net.chompsoftware.k6502.hardware.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test


@ExperimentalUnsignedTypes
class FlagOperationsTest {
    @Nested
    inner class ClearCarry {
        @Test
        fun `Should reset carry flag`() {
            val memory = Memory(setupMemory(InstructionSet.clc.u))
            val state = CpuState(isCarryFlag = true)
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2L,
                    programCounter = 0x01,
                    isCarryFlag = false
            )
        }
    }

    @Nested
    inner class SetCarry {
        @Test
        fun `Should set carry flag`() {
            val memory = Memory(setupMemory(InstructionSet.sec.u))
            val state = CpuState(isCarryFlag = false)
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2L,
                    programCounter = 0x01,
                    isCarryFlag = true
            )
        }
    }

    @Nested
    inner class ClearDecimal {
        @Test
        fun `Should reset decimal flag`() {
            val memory = Memory(setupMemory(InstructionSet.cld.u))
            val state = CpuState(isDecimalFlag = true)
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2,
                    programCounter = 0x01,
                    isDecimalFlag = false
            )
        }
    }

    @Nested
    inner class SetDecimal {
        @Test
        fun `Should set decimal flag`() {
            val memory = Memory(setupMemory(InstructionSet.sed.u))
            val state = CpuState(isDecimalFlag = false)
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2L,
                    programCounter = 0x01,
                    isDecimalFlag = true
            )
        }
    }

    @Nested
    inner class ClearInterrupt {
        @Test
        fun `Should reset interrupt flag`() {
            val memory = Memory(setupMemory(InstructionSet.cli.u))
            val state = CpuState(isInterruptDisabledFlag = true)
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2,
                    programCounter = 0x01,
                    isInterruptDisabledFlag = false
            )
        }
    }

    @Nested
    inner class SetInterrupt {
        @Test
        fun `Should set interrupt flag`() {
            val memory = Memory(setupMemory(InstructionSet.sei.u))
            val state = CpuState(isInterruptDisabledFlag = false)
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2L,
                    programCounter = 0x01,
                    isInterruptDisabledFlag = true
            )
        }
    }

    @Nested
    inner class ClearOverflow {
        @Test
        fun `Should reset overflow flag`() {
            val memory = Memory(setupMemory(InstructionSet.clv.u))
            val state = CpuState(isOverflowFlag = true)
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2,
                    programCounter = 0x01,
                    isOverflowFlag = false
            )
        }
    }
}