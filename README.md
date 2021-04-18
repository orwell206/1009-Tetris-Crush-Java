### Project Description 
Decide and state which game your team will be working on,
- The objection of the project is to create a `Tetris` game.

### Tool Name
Discuss and come up with a catchy name for your application,
- `Tetris Crush 1009`
-----
### MoSCoW Matrix
Brainstorm on what features are critical for your project. Also discuss the properties that you good to have and would include if time permits. Submit the MoSCow matrix https://en.wikipedia.org/wiki/MoSCoW_method for your project. It should include

**Must have:**
- [x] 2 players
- [x] Tetris board
- [x] Score board 
- [x] Player lives
- [x] Multiple Tetris block 
- [x] Ability to rotate Tetris block
- [x] Separate engines: Game engine, Player Engine, Audio Engine, UI Engine
- [x] Visually pleasing
- [x] GUI 

##### Should have:
- [x] Main menu
- [x] Game can be paused

##### Could have:
- [x] Soundtrack
- [x] Multiple levels
- [ ] Smooth animation/Effect

##### Would have:
- [x] Settings menu
- [x] Persistent scoreboard

##### Lab 4, Task4:
- Used *abstract* in `TetrominoShape`
- Used *interfaces* for `KeyBinding`, `GameBoard` and `TetrominoRotation`
- Used *polymorphism* for `PlayMusic()` in `Audio` , `render()` in `GameBoard` and `GetMethods` in `Levels`

##### Lab 5, Task3:
- Settings class: catch I/O Exception if the setting file is not found
- MainFrame class: catch Number Format Exception and Null Pointer Exception if data retreived from the file is invalid, such as the value of audio volume is some random characters
- BgMusic and SoundEffect classes: catch I/O Exception and Unsupported Audio File Exception if the audio file is not found or not in WAV format

-----
### Credits 
- junhui-f
- tanchiawei
- orwell206
- shufenlim
- YangYuqin23
