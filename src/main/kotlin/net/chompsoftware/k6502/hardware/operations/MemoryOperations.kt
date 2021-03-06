package net.chompsoftware.k6502.hardware.operations

import net.chompsoftware.k6502.hardware.CpuState
import net.chompsoftware.k6502.hardware.InstructionSet
import net.chompsoftware.k6502.hardware.RamInterface


@ExperimentalUnsignedTypes
internal object MemoryOperations {
    val loadAccumulator = { instruction: InstructionSet, state: CpuState, value: UInt ->
        state.copyRelativeWithA(instruction, value)
    }

    val loadx = { instruction: InstructionSet, state: CpuState, value: UInt ->
        state.copyRelativeWithX(instruction, value)
    }

    val loady = { instruction: InstructionSet, state: CpuState, value: UInt ->
        state.copyRelativeWithY(instruction, value)
    }

    val storeAccumulator = { instruction: InstructionSet, state: CpuState, memory: RamInterface, position: UInt ->
        memory[position] = state.aRegister.toUByte()
        state.incrementByInstruction(instruction)
    }

    val storeX = { instruction: InstructionSet, state: CpuState, memory: RamInterface, position: UInt ->
        memory[position] = state.xRegister.toUByte()
        state.incrementByInstruction(instruction)
    }

    val storeY = { instruction: InstructionSet, state: CpuState, memory: RamInterface, position: UInt ->
        memory[position] = state.yRegister.toUByte()
        state.incrementByInstruction(instruction)
    }
}