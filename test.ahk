F3::
Macro1:
WinActivate,  ahk_class tooltips_class32
Sleep, 333
WinActivate, 10.0.0.22 - Remote Desktop Connection ahk_class TscShellContainerClass
Sleep, 333
Send, 1.0
Sleep, 10
Send, {Tab}
Sleep, 10
Send, 19.875
Sleep, 10
Send, {Tab}
Sleep, 10
Send, 29.75
Sleep, 10
Click, 1150, 475 Left, , Down
Sleep, 10
Click, 1150, 475 Left, , Up
Sleep, 500
return
