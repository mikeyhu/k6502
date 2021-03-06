package net.chompsoftware.k6502.hardware.operations

import io.kotest.matchers.shouldBe
import net.chompsoftware.k6502.hardware.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test


@ExperimentalUnsignedTypes
class MemoryOperationsTest {
    @Nested
    inner class LoadAccumulator {
        @Test
        fun `Should handle LoaDAcc instruction and set accumulator`() {
            val memory = Memory(setupMemory(InstructionSet.lda_i.u, 0x08u))
            val state = CpuState()
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2L,
                    programCounter = 0x02,
                    aRegister = 0x08u
            )
        }

        @Test
        fun `Should handle LoaDAcc instruction, set accumulator and set negative flag`() {
            val memory = Memory(setupMemory(InstructionSet.lda_i.u, 0x80u))
            val state = CpuState()
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2L,
                    programCounter = 0x02,
                    aRegister = 0x80u,
                    isNegativeFlag = true
            )
        }

        @Test
        fun `Should handle LoaDAcc instruction, set accumulator and set zero flag`() {
            val memory = Memory(setupMemory(InstructionSet.lda_i.u, 0x0u))
            val state = CpuState()
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2L,
                    programCounter = 0x02,
                    aRegister = 0x0u,
                    isZeroFlag = true
            )
        }

        @Test
        fun `Should handle LoaDAcc instruction and set accumulator using Absolute addressing`() {
            val memory = Memory(setupMemory(InstructionSet.lda_ab.u, 0x04u, 0x00u, 0x00u, 0x11u))
            val state = CpuState()
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 4L,
                    programCounter = 0x03,
                    aRegister = 0x11u
            )
        }
    }

    @Nested
    inner class LoadX {
        @Test
        fun `Should handle LoaDX instruction and set accumulator`() {
            val memory = Memory(setupMemory(InstructionSet.ldx_i.u, 0x08u))
            val state = CpuState()
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2L,
                    programCounter = 0x02,
                    xRegister = 0x08u
            )
        }

        @Test
        fun `Should handle LoaDX instruction, set accumulator and set negative flag`() {
            val memory = Memory(setupMemory(InstructionSet.ldx_i.u, 0x80u))
            val state = CpuState()
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2L,
                    programCounter = 0x02,
                    xRegister = 0x80u,
                    isNegativeFlag = true
            )
        }

        @Test
        fun `Should handle LoaDX instruction, set accumulator and set zero flag`() {
            val memory = Memory(setupMemory(InstructionSet.ldx_i.u, 0x0u))
            val state = CpuState()
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2L,
                    programCounter = 0x02,
                    xRegister = 0x0u,
                    isZeroFlag = true
            )
        }

        @Test
        fun `Should handle LoaDX instruction with zy addressing`() {
            val memory = Memory(setupMemory(InstructionSet.ldx_zy.u, 0xf0u))
            memory[0xf1u] = 0x0fu
            val state = CpuState(yRegister = 0x1u)
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 4L,
                    programCounter = 0x02,
                    xRegister = 0x0fu
            )
        }

        @Test
        fun `Should handle LoaDX instruction with zy addressing with wrap around`() {
            val memory = Memory(setupMemory(InstructionSet.ldx_zy.u, 0xf0u))
            memory[0x10u] = 0x0fu
            val state = CpuState(yRegister = 0x20u)
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 4L,
                    programCounter = 0x02,
                    xRegister = 0x0fu
            )
        }
    }

    @Nested
    inner class LoadY {
        @Test
        fun `Should handle LoaDY instruction and set accumulator`() {
            val memory = Memory(setupMemory(InstructionSet.ldy_i.u, 0x08u))
            val state = CpuState()
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2L,
                    programCounter = 0x02,
                    yRegister = 0x08u
            )
        }

        @Test
        fun `Should handle LoaDY instruction, set accumulator and set negative flag`() {
            val memory = Memory(setupMemory(InstructionSet.ldy_i.u, 0x80u))
            val state = CpuState()
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2L,
                    programCounter = 0x02,
                    yRegister = 0x80u,
                    isNegativeFlag = true
            )
        }

        @Test
        fun `Should handle LoaDY instruction, set accumulator and set zero flag`() {
            val memory = Memory(setupMemory(InstructionSet.ldy_i.u, 0x0u))
            val state = CpuState()
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 2L,
                    programCounter = 0x02,
                    yRegister = 0x0u,
                    isZeroFlag = true
            )
        }
    }

    @Nested
    inner class StoreAccumulator {
        @Test
        fun `Should store accumulator in memory using Zero Page addressing`() {
            val memory = Memory(setupMemory(InstructionSet.sta_z.u, 0x02u, 0x05u))
            val state = CpuState(aRegister = 0x11u)
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 3,
                    programCounter = 0x02
            )
            memory.readUInt(0x02) shouldBe 0x11u
        }

        @Test
        fun `Should store accumulator in memory using Absolute addressing`() {
            val memory = Memory(setupMemory(InstructionSet.sta_ab.u, 0x05u, 0x01u))
            val state = CpuState(aRegister = 0x11u)
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 4,
                    programCounter = 0x03
            )
            memory.readUInt(0x105) shouldBe 0x11u
        }
    }

    @Nested
    inner class StoreX {
        @Test
        fun `Should store X in memory using Zero Page addressing`() {
            val memory = Memory(setupMemory(InstructionSet.stx_z.u, 0x02u, 0x05u))
            val state = CpuState(xRegister = 0x11u)
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 3,
                    programCounter = 0x02
            )
            memory.readUInt(0x02) shouldBe 0x11u
        }

        @Test
        fun `Should store X in memory using Absolute addressing`() {
            val memory = Memory(setupMemory(InstructionSet.stx_ab.u, 0x05u, 0x01u))
            val state = CpuState(xRegister = 0x11u)
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 4,
                    programCounter = 0x03
            )
            memory.readUInt(0x105) shouldBe 0x11u
        }
    }

    @Nested
    inner class StoreY {
        @Test
        fun `Should store Y in memory using Zero Page addressing`() {
            val memory = Memory(setupMemory(InstructionSet.sty_z.u, 0x02u, 0x05u))
            val state = CpuState(yRegister = 0x11u)
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 3,
                    programCounter = 0x02
            )
            memory.readUInt(0x02) shouldBe 0x11u
        }

        @Test
        fun `Should store Y in memory using Absolute addressing`() {
            val memory = Memory(setupMemory(InstructionSet.sty_ab.u, 0x05u, 0x01u))
            val state = CpuState(yRegister = 0x11u)
            val cpu = Cpu()
            cpu.run(state, memory) shouldBe state.copy(
                    cycleCount = 4,
                    programCounter = 0x03
            )
            memory.readUInt(0x105) shouldBe 0x11u
        }
    }

}