# YouTube Playlist Generator

### About
This tool makes it easy to manage YouTube playlists without an account, by simply keeping a list of playlists in a source file (playlistsSourced.md),
and adding new links to playlists files as desired.

### How it works
This tool is currently configured to work with the knowledge management system Logseq, using Markdown files (.md).

The script searches through the file playlistsSources.md in the pages directory, and grabs each playlist name from this file.

For each one of these playlists, the tool reads from the appropriate page file (.md) all of the individual video links
and generates a playlist link for these which is written to the files generatedPlaylistsTitled.md (with title),
and generatedPlaylistsUntitled.md, where the finished links can be accessed.
