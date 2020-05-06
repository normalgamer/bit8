# bit8
Bit8 is an invented 8 bit computer emulator with 4K addressable memory for ROM and variables, made as a challenge so I learn Java programming.

## Why
Why not? I've always wanted to learn how emulators work and create mine, so I found out about the Chip 8, some sort of invented CPU/interpreter made for programmers to develop games at home. But after failing to start doing so I decided to create my own system, and now I understand how to make emulators :D !

## Hardware overview
- CPU: a downgraded 6502, similar to a Chip 8
- Memory: 4K for program and variables
- Graphics: not added yet (will use OpenGL)

#### Others
- 2 general purpose registers: X and Y
- A Program Counter and a Program Counter Stack for JSR and RTS (updates automatically)

## Work in progress
Right now, it's pretty barebones, though I'm planning on adding support for graphics, input, etc. I'm planning on adding more opcodes before graphic capabilities.
Supported opcodes
- 00  -  NOP            - No OPeration
- 20  -  INC            - INCrement (always absolute)
- 21  -  DEC            - DECrement (always absolute)
- 25  -  INX            - INcrement X
- 26  -  DEX            - DEcrement X
- 2A  -  INY            - INcrement Y
- 2B  -  DEY            - DEcrement Y
- 2E  -  TXY            - Transfer X into Y
- 2F  -  TYX            - Transfer Y into X
- 66  -  HLT            - HaLT, cpu ded, F (execute order 66 >:D)
- A0  -  LDX_IMMEDIATE  -  LoaD into X
- A1  -  STX            -  STore X (always absolute)
- AA  -  LDX_ABSOLUTE   - LoaD into X
- B0  -  LDY_IMMEDIATE  - LoaD into Y
- B1  -  STY            - STore Y (always absolute)
- BB  -  LDY_ABSOLUTE   - LoaD into Y
- D0  -  JMP            - JuMP (always absolute)
- E0  -  JSR            - Jump to SubRoutine (always absolute)
- EA  -  RTS            - ReTurn from Subroutine
