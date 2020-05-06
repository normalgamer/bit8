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
- 00  -  NOP
- 20  -  INC (absolute)
- 21  -  DEC (absolute)
- 25  -  INX
- 26  -  DEX
- 2A  -  INY
- 2B  -  DEY
- 66  -  HLT - hang, cpu ded, F (execute order 66 >:D)
- A0  -  LDX (immediate)
- A1  -  STX (absolute)
- AA  -  LDX (absolute addr)
- B0  -  LDY (immediate)
- B1  -  STY (absolute)
- BB  -  LDY (absolute addr)
- D0  -  JMP (next 2 byte addr)
- E0  -  JSR (next 2 byte addr)
- EA  -  RTS (pcStack)
