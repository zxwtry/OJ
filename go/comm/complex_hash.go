package comm

import "hash/crc32"

func CommComplexHashCRC(uid string) uint32 {
	return crc32.ChecksumIEEE([]byte(uid))
}
