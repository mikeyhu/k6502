package net.chompsoftware.k6502.hardware.video

import java.awt.Transparency
import java.awt.color.ColorSpace
import java.awt.image.*


val teletextCharacters = mapOf(
        0x0 to byteArrayOf(// 0x0 ' ' set to space same as 0x20
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x20 to byteArrayOf(// 0x20 ' '
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x21 to byteArrayOf(// 0x21 '!'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x22 to byteArrayOf(// 0x22 '"'
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 0, 1, 0,
                0, 0, 1, 0, 1, 0,
                0, 0, 1, 0, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x23 to byteArrayOf(// 0x23 ' British Pound'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 1, 0,
                0, 0, 1, 0, 0, 1,
                0, 0, 1, 0, 0, 0,
                0, 1, 1, 1, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x24 to byteArrayOf(// 0x24 '$'
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 1, 0, 1, 0, 1,
                0, 1, 0, 1, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 1, 0, 1,
                0, 1, 0, 1, 0, 1,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x25 to byteArrayOf(// 0x25 '%'
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 0, 0, 0,
                0, 1, 1, 0, 0, 1,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 1, 0, 0, 1, 1,
                0, 0, 0, 0, 1, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x26 to byteArrayOf(// 0x26 '&'
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 1, 0, 1, 0, 0,
                0, 1, 0, 1, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 1, 0, 1, 0, 1,
                0, 1, 0, 0, 1, 0,
                0, 0, 1, 1, 0, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x27 to byteArrayOf(// 0x27 '''
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x28 to byteArrayOf(// 0x28 '('
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x29 to byteArrayOf(// 0x29 ')'
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x2a to byteArrayOf(// 0x2a '*'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 1, 0, 1, 0, 1,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 1, 0, 1, 0, 1,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x2b to byteArrayOf(// 0x2b '+'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 1, 1, 1, 1, 1,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x2c to byteArrayOf(// 0x2c ','
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x2d to byteArrayOf(// 0x2d '-'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x2e to byteArrayOf(// 0x2e '.'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x2f to byteArrayOf(// 0x2f '/'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x30 to byteArrayOf(// 0x30 '0'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 0, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 0, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x31 to byteArrayOf(// 0x31 '1'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x32 to byteArrayOf(// 0x32 '2'
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 1,
                0, 0, 0, 1, 1, 0,
                0, 0, 1, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x33 to byteArrayOf(// 0x33 '3'
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 1, 1, 0,
                0, 0, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x34 to byteArrayOf(// 0x34 '4'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 1, 1, 0,
                0, 0, 1, 0, 1, 0,
                0, 1, 0, 0, 1, 0,
                0, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x35 to byteArrayOf(// 0x35 '5'
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 1,
                0, 1, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x36 to byteArrayOf(// 0x36 '6'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 1, 0,
                0, 0, 1, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x37 to byteArrayOf(// 0x37 '7'
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x38 to byteArrayOf(// 0x38 '8'
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x39 to byteArrayOf(// 0x39 '9'
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 1, 0,
                0, 0, 1, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x3a to byteArrayOf(// 0x3a ':'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x3b to byteArrayOf(// 0x3b ';'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x3c to byteArrayOf(// 0x3c '<'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x3d to byteArrayOf(// 0x3d '='
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x3e to byteArrayOf(// 0x3e '>'
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x3f to byteArrayOf(// 0x3f '?'
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x40 to byteArrayOf(// 0x40 '@'
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 1, 1, 1,
                0, 1, 0, 1, 0, 1,
                0, 1, 0, 1, 1, 1,
                0, 1, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x41 to byteArrayOf(// 0x41 'A'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 0, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 1, 1, 1, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x42 to byteArrayOf(// 0x42 'B'
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x43 to byteArrayOf(// 0x43 'C'
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x44 to byteArrayOf(// 0x44 'D'
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x45 to byteArrayOf(// 0x45 'E'
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 1,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x46 to byteArrayOf(// 0x46 'F'
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 1,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x47 to byteArrayOf(// 0x47 'G'
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 1, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x48 to byteArrayOf(// 0x48 'H'
                0, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 1, 1, 1, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x49 to byteArrayOf(// 0x49 'I'
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x4a to byteArrayOf(// 0x4a 'J'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x4b to byteArrayOf(// 0x4b 'K'
                0, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 1, 0,
                0, 1, 0, 1, 0, 0,
                0, 1, 1, 0, 0, 0,
                0, 1, 0, 1, 0, 0,
                0, 1, 0, 0, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x4c to byteArrayOf(// 0x4c 'L'
                0, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x4d to byteArrayOf(// 0x4d 'M'
                0, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 1, 0, 1, 1,
                0, 1, 0, 1, 0, 1,
                0, 1, 0, 1, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x4e to byteArrayOf(// 0x4e 'N'
                0, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 1, 0, 0, 1,
                0, 1, 0, 1, 0, 1,
                0, 1, 0, 0, 1, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x4f to byteArrayOf(// 0x4f 'O'
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x50 to byteArrayOf(// 0x50 'P'
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x51 to byteArrayOf(// 0x51 'Q'
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 1, 0, 1,
                0, 1, 0, 0, 1, 0,
                0, 0, 1, 1, 0, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x52 to byteArrayOf(// 0x52 'R'
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 1, 1, 1, 0,
                0, 1, 0, 1, 0, 0,
                0, 1, 0, 0, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x53 to byteArrayOf(// 0x53 'S'
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x54 to byteArrayOf(// 0x54 'T'
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 1,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x55 to byteArrayOf(// 0x55 'U'
                0, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x56 to byteArrayOf(// 0x56 'V'
                0, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 0, 1, 0,
                0, 0, 1, 0, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x57 to byteArrayOf(// 0x57 'W'
                0, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 1, 0, 1,
                0, 1, 0, 1, 0, 1,
                0, 1, 0, 1, 0, 1,
                0, 0, 1, 0, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x58 to byteArrayOf(// 0x58 'X'
                0, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 0, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 0, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x59 to byteArrayOf(// 0x59 'Y'
                0, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 0, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x5a to byteArrayOf(// 0x5a 'Z'
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x5b to byteArrayOf(// 0x5b Left Arrow
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 1, 1, 1, 1, 1,
                0, 0, 1, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x5c to byteArrayOf(// 0x5c ' 1/2 symbol'
                0, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 1, 1, 0,
                0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 1, 1),
        0x5d to byteArrayOf(// 0x5d Right Arrow
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 1, 0,
                0, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x5e to byteArrayOf(// 0x5e Up Arrow
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 1, 0, 1, 0, 1,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x5f to byteArrayOf(// 0x5f '#'
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 0, 1, 0,
                0, 0, 1, 0, 1, 0,
                0, 1, 1, 1, 1, 1,
                0, 0, 1, 0, 1, 0,
                0, 1, 1, 1, 1, 1,
                0, 0, 1, 0, 1, 0,
                0, 0, 1, 0, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x60 to byteArrayOf(// 0x60 '_'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x61 to byteArrayOf(// 0x61 'a'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x62 to byteArrayOf(// 0x62 'b'
                0, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x63 to byteArrayOf(// 0x63 'c'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 1,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x64 to byteArrayOf(// 0x64 'd'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x65 to byteArrayOf(// 0x65 'e'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 1, 1, 1, 1,
                0, 1, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x66 to byteArrayOf(// 0x66 'f'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x67 to byteArrayOf(// 0x67 'g'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 0),
        0x68 to byteArrayOf(// 0x68 'h'
                0, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x69 to byteArrayOf(// 0x69 'i'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x6a to byteArrayOf(// 0x6a 'j'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x6b to byteArrayOf(// 0x6b 'k'
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 1, 0, 0, 1,
                0, 0, 1, 0, 1, 0,
                0, 0, 1, 1, 0, 0,
                0, 0, 1, 0, 1, 0,
                0, 0, 1, 0, 0, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x6c to byteArrayOf(// 0x6c 'l'
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x6d to byteArrayOf(// 0x6d 'm'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 0, 1, 0,
                0, 1, 0, 1, 0, 1,
                0, 1, 0, 1, 0, 1,
                0, 1, 0, 1, 0, 1,
                0, 1, 0, 1, 0, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x6e to byteArrayOf(// 0x6e 'n'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x6f to byteArrayOf(// 0x6f 'o'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x70 to byteArrayOf(// 0x70 'p'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 1, 1, 1, 0,
                0, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0),
        0x71 to byteArrayOf(// 0x71 'q'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 1),
        0x72 to byteArrayOf(// 0x72 'r'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 0, 1, 1,
                0, 0, 1, 1, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x73 to byteArrayOf(// 0x73 's'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 1,
                0, 1, 0, 0, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 1,
                0, 1, 1, 1, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x74 to byteArrayOf(// 0x74 't'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 1, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x75 to byteArrayOf(// 0x75 'u'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x76 to byteArrayOf(// 0x76 'v'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 0, 1, 0,
                0, 0, 1, 0, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x77 to byteArrayOf(// 0x77 'w'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 1, 0, 1,
                0, 1, 0, 1, 0, 1,
                0, 0, 1, 0, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x78 to byteArrayOf(// 0x78 'x'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 0, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 0, 1, 0,
                0, 1, 0, 0, 0, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x79 to byteArrayOf(// 0x79 'y'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 1, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 1,
                0, 0, 1, 1, 1, 0),
        0x7a to byteArrayOf(// 0x7a 'z'
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 1, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x7b to byteArrayOf(// 0x7b 'one fourth symbol'
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 1, 0, 0, 0,
                0, 0, 1, 0, 0, 1,
                0, 0, 0, 0, 1, 1,
                0, 0, 0, 1, 0, 1,
                0, 0, 0, 1, 1, 1,
                0, 0, 0, 0, 0, 1),
        0x7c to byteArrayOf(// 0x7c '|'
                0, 0, 0, 0, 0, 0,
                0, 0, 1, 0, 1, 0,
                0, 0, 1, 0, 1, 0,
                0, 0, 1, 0, 1, 0,
                0, 0, 1, 0, 1, 0,
                0, 0, 1, 0, 1, 0,
                0, 0, 1, 0, 1, 0,
                0, 0, 1, 0, 1, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x7d to byteArrayOf(// 0x7d ' three fourths symbol'
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 1, 1, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 1, 1, 0, 0, 1,
                0, 0, 0, 0, 1, 1,
                0, 0, 0, 1, 0, 1,
                0, 0, 0, 1, 1, 1,
                0, 0, 0, 0, 0, 1),
        0x7e to byteArrayOf(// 0x7e Divide
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0),
        0x7f to byteArrayOf(// 0x7f Block
                0, 0, 0, 0, 0, 0,
                0, 1, 1, 1, 1, 1,
                0, 1, 1, 1, 1, 1,
                0, 1, 1, 1, 1, 1,
                0, 1, 1, 1, 1, 1,
                0, 1, 1, 1, 1, 1,
                0, 1, 1, 1, 1, 1,
                0, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0)
)

val TELETEXT_WIDTH = 6
val TELETEXT_HEIGHT = 10

private val HAS_PIXEL: Byte = 1

fun characterAsBufferedImage(char: ByteArray): BufferedImage {
    val intermediate = ByteArray(TELETEXT_WIDTH * TELETEXT_HEIGHT * 3)

    char.forEachIndexed { index, byte ->
        if(byte == HAS_PIXEL) {
            intermediate[index * 3] = 0xff.toByte()
            intermediate[index * 3 + 1] = 0xff.toByte()
            intermediate[index * 3 + 2] = 0xff.toByte()
        }
    }
    val dbb = DataBufferByte(intermediate, intermediate.size)
    val cm: ColorModel = ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), intArrayOf(8, 8, 8), false, false, Transparency.OPAQUE, DataBuffer.TYPE_BYTE)
    return BufferedImage(cm, Raster.createInterleavedRaster(dbb, TELETEXT_WIDTH, TELETEXT_HEIGHT, TELETEXT_WIDTH * 3, 3, intArrayOf(0, 1, 2), null), false, null)
}